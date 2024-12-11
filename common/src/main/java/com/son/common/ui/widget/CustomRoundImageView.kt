package com.son.common.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.son.common.R

class CustomRoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val path = Path()
    private val rect = RectF()
    private val radii = FloatArray(8) { 0f }
    private val borderPaint = Paint()
    private var borderWidth = 5f
    private var cornerRadius = 30f
    private var isShowBorder = false

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomRoundedImageView)
            val allCorners =
                typedArray.getDimension(R.styleable.CustomRoundedImageView_cornerRadius, 0f)
            val topLeft = typedArray.getDimension(
                R.styleable.CustomRoundedImageView_cornerRadiusTopLeft,
                allCorners
            )
            val topRight = typedArray.getDimension(
                R.styleable.CustomRoundedImageView_cornerRadiusTopRight,
                allCorners
            )
            val bottomRight = typedArray.getDimension(
                R.styleable.CustomRoundedImageView_cornerRadiusBottomRight,
                allCorners
            )
            val bottomLeft = typedArray.getDimension(
                R.styleable.CustomRoundedImageView_cornerRadiusBottomLeft,
                allCorners
            )

            setCornerRadii(topLeft, topRight, bottomRight, bottomLeft)
            typedArray.recycle()
        }
        borderPaint.color = ContextCompat.getColor(context, R.color.rounded_image_border_color)
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = borderWidth
    }

    private fun setCornerRadii(topLeft: Float, topRight: Float, bottomRight: Float, bottomLeft: Float) {
        cornerRadius = topLeft
        radii[0] = topLeft
        radii[1] = topLeft
        radii[2] = topRight
        radii[3] = topRight
        radii[4] = bottomRight
        radii[5] = bottomRight
        radii[6] = bottomLeft
        radii[7] = bottomLeft
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        rect.set(0f, 0f, width.toFloat(), height.toFloat())
        path.reset()
        path.addRoundRect(rect, radii, Path.Direction.CW)
        canvas.clipPath(path)
        super.onDraw(canvas)
        if (isShowBorder) {
            canvas.drawRoundRect(rect, cornerRadius, cornerRadius, borderPaint)
        }
    }

    fun setShowBorder(isShowBorder: Boolean) {
        this.isShowBorder = isShowBorder
        invalidate()
    }
}