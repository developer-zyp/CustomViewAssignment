package com.zyp.customviewassignment.views.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.zyp.customviewassignment.R

class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {

        private const val DEFAULT_BORDER_COLOR = Color.WHITE

    }

    private var borderColor = DEFAULT_BORDER_COLOR

    private val path = Path()

    //stroke paint
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var borderWidth = 6f

    init {
        context.withStyledAttributes(attrs, R.styleable.CircleImageView) {
            borderColor = getColor(R.styleable.CircleImageView_borderColor, DEFAULT_BORDER_COLOR)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (drawable == null) {
            super.onDraw(canvas)
            return
        }

        val radius = width.coerceAtMost(height) / 2f

        path.addCircle(
            width / 2f,
            height / 2f,
            radius,
            Path.Direction.CW
        )

        canvas?.clipPath(path)

        super.onDraw(canvas)

        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        canvas?.drawCircle(width / 2f, height / 2f, radius - borderWidth / 2f, paint)

    }

}