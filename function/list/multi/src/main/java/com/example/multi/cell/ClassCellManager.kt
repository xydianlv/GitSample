package com.example.multi.cell

class ClassCellManager : AbsCellManager<Class<out Any>, Any>() {

    override fun loadKeyFromData(itemData: Any): Class<out Any> {
        return itemData::class.java
    }
}