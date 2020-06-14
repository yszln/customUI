package com.yszln.advancedui.view.schedule

data class ScheduleItem(val title: String) {
    var isSelect: Boolean=false

    constructor(title: String, isSelect: Boolean) : this(title){
        this.isSelect=isSelect
    }


}