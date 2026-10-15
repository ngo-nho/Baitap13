package com.ute.baitap13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.baitap13.databinding.ActivityMainBinding
import model.Student
import utils.toAcademicRanking
import utils.toast
import utils.trimmedText


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    // ==========================================
    // SINH VIÊN MẶC ĐỊNH
    // ==========================================

    private val defaultStudent = Student(
        id = "2415053122333",
        name = "Ngô Thị Nhớ",
        className = "24T3",
        email = "2415053122333@ute.udn.vn",
        gpa = 3.75
    )


    // Sinh viên hiện tại
    private var currentStudent = defaultStudent


    // ==========================================
    // ON CREATE
    // ==========================================

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Khởi tạo ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        // ==========================================
        // KHÔI PHỤC TRẠNG THÁI KHI XOAY MÀN HÌNH
        // ==========================================

        savedInstanceState
            ?.getSerializable(KEY_STUDENT)
            ?.let {

                currentStudent = it as Student
            }


        // ==========================================
        // HIỂN THỊ THÔNG TIN SINH VIÊN
        // ==========================================

        bindStudentData(currentStudent)


        // ==========================================
        // NÚT CẬP NHẬT GPA
        // ==========================================

        binding.btnUpdateGpa.setOnClickListener {

            // Lấy dữ liệu từ EditText
            val input = binding.edtGpaInput.trimmedText()


            // Chuyển String thành Double
            val gpa = input.toDoubleOrNull()


            // ======================================
            // KIỂM TRA GPA
            // ======================================

            if (gpa == null || gpa !in 0.0..4.0) {

                binding.edtGpaInput.error =
                    "GPA phải từ 0.0 đến 4.0"

                binding.edtGpaInput.requestFocus()

                toast("GPA không hợp lệ!")

                return@setOnClickListener
            }


            // ======================================
            // CẬP NHẬT BẰNG COPY()
            // ======================================

            currentStudent = currentStudent.copy(
                gpa = gpa
            )


            // ======================================
            // CẬP NHẬT GIAO DIỆN
            // ======================================

            bindStudentData(currentStudent)

            toast("Đã cập nhật GPA thành công!")
        }


        // ==========================================
        // NÚT KHÔI PHỤC
        // ==========================================

        binding.btnReset.setOnClickListener {

            // Khôi phục sinh viên mặc định
            currentStudent = defaultStudent


            // Hiển thị lại thông tin
            bindStudentData(currentStudent)


            toast("Đã khôi phục thông tin mặc định!")
        }
    }


    // ==========================================
    // HÀM HIỂN THỊ DỮ LIỆU SINH VIÊN
    // ==========================================

    private fun bindStudentData(student: Student) {

        with(binding) {

            // --------------------------------------
            // TÊN SINH VIÊN
            // --------------------------------------

            tvStudentName.text =
                student.name


            // --------------------------------------
            // MSSV + LỚP
            // --------------------------------------

            tvStudentDetails.text =
                "MSSV: ${student.id} • Lớp: ${student.className}"


            // --------------------------------------
            // EMAIL
            // --------------------------------------

            tvStudentEmail.text =
                "Email: ${student.email}"


            // --------------------------------------
            // GPA + XẾP LOẠI
            // --------------------------------------

            tvGpaBadge.text =
                "${student.gpa} GPA • ${student.gpa.toAcademicRanking()}"


            // --------------------------------------
            // HIỂN THỊ GPA TRONG EDITTEXT
            // --------------------------------------

            edtGpaInput.setText(
                student.gpa.toString()
            )


            // --------------------------------------
            // KIỂM TRA SINH VIÊN XUẤT SẮC
            // --------------------------------------

            if (student.isHonorStudent) {

                tvHonorStatus.text =
                    "🏆 Sinh viên xuất sắc"

            } else {

                tvHonorStatus.text =
                    "Sinh viên chưa đạt danh hiệu xuất sắc"
            }
        }
    }


    // ==========================================
    // LƯU TRẠNG THÁI KHI XOAY MÀN HÌNH
    // ==========================================

    override fun onSaveInstanceState(
        outState: Bundle
    ) {

        outState.putSerializable(
            KEY_STUDENT,
            currentStudent
        )

        super.onSaveInstanceState(outState)
    }


    // ==========================================
    // KEY LƯU DỮ LIỆU
    // ==========================================

    companion object {

        private const val KEY_STUDENT =
            "KEY_STUDENT"
    }
}