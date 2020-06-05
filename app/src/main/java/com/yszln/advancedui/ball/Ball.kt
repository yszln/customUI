package com.yszln.advancedui.ball


data class Ball(
    var color: Int=0,//像素点的颜色值
    var x: Float=0f,//粒子圆心坐标x
    var y: Float=0f,//粒子圆心坐标y
    var r: Float=0f, //粒子半径
    var vx: Float=5f,//粒子运动水平方向速度
    var vy: Float=5f,//粒子运动垂直方向速度
    var ax: Float=0f,//粒子运动水平方向加速度
    var ay: Float=0f//粒子于东垂直方向加速度
) {

}