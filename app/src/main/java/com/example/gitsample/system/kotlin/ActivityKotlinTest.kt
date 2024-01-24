package com.example.gitsample.system.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.gitsample.base.BaseActivity
import com.example.gitsample.base.PageType
import com.example.gitsample.databinding.ActivityKotlinTestBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Created by wyyu on 2024/1/24.
 **/
class ActivityKotlinTest : BaseActivity() {

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, ActivityKotlinTest::class.java))
        }
    }

    private lateinit var binding: ActivityKotlinTestBinding

    private var startTime: Long = 0L
    private var mainJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivity()
    }

    private fun initActivity() {
        initToolbar()
        initCode()
        initClick()
    }

    private fun initToolbar() {
        binding.toolbar.initShow(PageType.KOTLIN_TEST.title)
    }

    private fun initCode(){
        val codeInfo = StringBuilder("")
            .append("startTime = System.currentTimeMillis()\n")
            .append("log(\"onStart\")\n")
            .append("runBlocking {\n")
            .append("   delay(1000L)\n")
            .append("   log(\"runBlocking\")\n")
            .append("}\n")
            .append("mainJob = CoroutineScope(Dispatchers.Main).launch {\n")
            .append("   delay(2000L)\n")
            .append("   log(\"CoroutineScope(Dispatchers.Main).launch\")\n")
            .append("}\n")
            .append("lifecycleScope.launch {\n")
            .append("   val resultIO = withContext(Dispatchers.IO) {\n")
            .append("       delay(3000L)\n")
            .append("       \"withContext(Dispatchers.IO)\"\n")
            .append("   }\n")
            .append("   log(resultIO)\n")
            .append("   val resultMain = withContext(Dispatchers.Main) {\n")
            .append("       delay(4000L)\n")
            .append("       \"withContext(Dispatchers.Main)\"}\n")
            .append("   }\n")
            .append("   launch {\n")
            .append("       delay(5000L)\n")
            .append("       log(\"only launch\")\n")
            .append("   }\n")
            .append("   val deferredA = async {\n")
            .append("       delay(6000L)\n")
            .append("       \"deferredA\"\n")
            .append("   }\n")
            .append("   val deferredB = async {\n")
            .append("       delay(7000L)\n")
            .append("        \"deferredB\"\n")
            .append("   }\n")
            .append("   log(deferredB.await())\n")
            .append("   log(deferredA.await())\n")
            .append("}\n")
            .append("log(\"onEnd\")\n")
        binding.code.text = codeInfo
    }

    private fun initClick() {
        binding.run.setOnClickListener {
            testKotlin()
        }
    }

    private fun testKotlin() {
        startTime = System.currentTimeMillis()
        log("onStart")
        runBlocking {
            delay(1000L)
            log("runBlocking")
        }
        mainJob = CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            log("CoroutineScope(Dispatchers.Main).launch")
        }
        lifecycleScope.launch {
            val resultIO = withContext(Dispatchers.IO) {
                delay(3000L)
                "withContext(Dispatchers.IO)"
            }
            log(resultIO)
            val resultMain = withContext(Dispatchers.Main) {
                delay(4000L)
                "withContext(Dispatchers.Main)"
            }
            log(resultMain)
            launch {
                delay(5000L)
                log("only launch")
            }
            val deferredA = async {
                delay(6000L)
                "deferredA"
            }
            val deferredB = async {
                delay(7000L)
                "deferredB"
            }
            log(deferredB.await())
            log(deferredA.await())
        }
        log("onEnd")
    }

    private fun log(logInfo: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            val divideTime = System.currentTimeMillis() - startTime
            binding.logList.insertLog("$divideTime -> $logInfo")
        }
    }
}