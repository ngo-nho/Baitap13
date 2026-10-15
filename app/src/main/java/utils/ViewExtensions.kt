package utils

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast

// ==========================================
// 1. HIỆN VIEW
// ==========================================

fun View.show() {
    visibility = View.VISIBLE
}


// ==========================================
// 2. ẨN VIEW HOÀN TOÀN
// ==========================================

fun View.gone() {
    visibility = View.GONE
}


// ==========================================
// 3. ẨN VIEW NHƯNG GIỮ VỊ TRÍ
// ==========================================

fun View.invisible() {
    visibility = View.INVISIBLE
}


// ==========================================
// 4. HIỂN THỊ TOAST
// ==========================================

fun Context.toast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(
        this,
        message,
        duration
    ).show()
}


// ==========================================
// 5. LẤY TEXT TỪ EDITTEXT
// ==========================================

fun EditText.trimmedText(): String {
    return text.toString().trim()
}


// ==========================================
// 6. XẾP LOẠI HỌC LỰC THEO GPA
// ==========================================

fun Double.toAcademicRanking(): String {

    return when {

        this >= 3.6 ->
            "Xuất sắc"

        this >= 3.2 ->
            "Giỏi"

        this >= 2.5 ->
            "Khá"

        this >= 2.0 ->
            "Trung bình"

        this >= 1.0 ->
            "Yếu"

        else ->
            "Kém"
    }
}