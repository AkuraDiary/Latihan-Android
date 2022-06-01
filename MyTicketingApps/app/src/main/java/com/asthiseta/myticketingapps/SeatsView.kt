package com.asthiseta.myticketingapps

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class SeatsView : View {
    constructor(context : Context) : super(context)

    constructor(context : Context, attrs : AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super
        (
        context,
        attrs,
        defStyleAttr
    )

    private val seats: ArrayList<Seat> = arrayListOf(
        Seat(id = 1, name = "A1", isBooked = false),
        Seat(id = 2, name = "A2", isBooked = false),
        Seat(id = 3, name = "B1", isBooked = false),
        Seat(id = 4, name = "B2", isBooked = false),
        Seat(id = 5, name = "C1", isBooked = false),
        Seat(id = 6, name = "C2", isBooked = false),
        Seat(id = 7, name = "D1", isBooked = false),
        Seat(id = 8, name = "D2", isBooked = false),

    )

    var seat: Seat? = null
    private val background = Paint()
    private val armrestPaint = Paint()
    private val bottomSeatPaint = Paint()
    private val mBounds = Rect()
    private val numberSeatPaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)
    val titlePaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for(seat in seats){
            drawSeat(canvas, seat)
        }
    }

    private fun drawSeat(canvas: Canvas?, seat: Seat) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val halfOfHeight = height / 2
        val halfOfWidth = width / 2

        var value = -600F

        for(i in 0..7){
            if (i.mod(2) == 0){
                seats[i].apply {
                    x = halfOfWidth - 300F
                    y = halfOfHeight + value
                }
            }else{
                seats[i].apply {
                    x = halfOfWidth+100F
                    y = halfOfHeight + value
                }
            }
            value += 300F
        }
    }
}