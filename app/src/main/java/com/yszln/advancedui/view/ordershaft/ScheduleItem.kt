package com.yszln.advancedui.view.ordershaft

data class ScheduleItem(val title: String) {
    var isSelect: Boolean=false

    constructor(title: String, isSelect: Boolean) : this(title){
        this.isSelect=isSelect
    }


}