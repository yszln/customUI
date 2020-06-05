package com.yszln.advancedui.ordershaft

data class OrderShaftBean(val title: String) {
    var isSelect: Boolean=false

    constructor(title: String, isSelect: Boolean) : this(title){
        this.isSelect=isSelect
    }


}