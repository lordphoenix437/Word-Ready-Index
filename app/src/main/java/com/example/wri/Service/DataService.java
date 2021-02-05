package com.example.wri.Service;

import com.example.wri.Model.Classs;
import com.example.wri.Model.Companys;
import com.example.wri.Model.MajorItem;
import com.example.wri.Model.Points;
import com.example.wri.Model.Requirement;
import com.example.wri.Model.Student_Teacher_Class_Point;
import com.example.wri.Model.Students;
import com.example.wri.Model.Teacher;
import com.example.wri.Model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @FormUrlEncoded
    @POST("users/register_stu.php")
    Call<Students> createstudent(
            @Field("nameStudent") String nameStudent,
            @Field("emailStudent") String emailStudent,
            @Field("passwordStudent") String passwordStudent,
            @Field("phoneStudent") String phoneStudent,
            @Field("majorStudent") String majorStudent);
    @FormUrlEncoded
    @POST("users/register_co.php")
    Call<Companys> createcompany(
            @Field("nameCompany") String nameCompany,
            @Field("emailCompany") String emailCompany,
            @Field("passwordCompany") String passwordCompany,
            @Field("phoneCompany") String phoneCompany,
            @Field("address") String address
    );
    @FormUrlEncoded
    @POST("users/login.php")
    Call<Users> login(
            @Field("email") String email,
            @Field("password") String password);
    //admin ......
    @FormUrlEncoded
    @POST("class/getall_class.php")
    Call<List<Classs>> getAllClass(
            @Field("keyword") String keyword
    );
    @FormUrlEncoded
    @POST("admin/getall_student_admin.php")
    Call<List<Students>> getAllStudent_admin(
            @Field("keyword") String keyword
    );
    @FormUrlEncoded
    @POST("admin/get_student.php")
    Call<List<Students>> getDetailStudent_admin(
            @Field("idstudent") String idstudent
    );
    @FormUrlEncoded
    @POST("teacher/get_allteacher.php")
    Call<List<Teacher>> getAllTeacher_admin(
            @Field("keyword") String keyword
    );
    @FormUrlEncoded
    @POST("teacher/get_detail_teacher.php")
    Call<List<Teacher>> getDetailTeacher_admin(
            @Field("codeTeacher") String codeTeacher
    );
    //Admin<->Class
    @FormUrlEncoded
    @POST("class/detail_class.php")
    Call<List<Classs>> getDetailClass_admin(
            @Field("idclass") String idclass
    );
    @FormUrlEncoded
    @POST("class/addstu_class.php")
    Call<Student_Teacher_Class_Point> connective_student_class(
            @Field("idClass") String idClass,
            @Field("idStudent") String idStudent
    );
    @FormUrlEncoded
    @POST("class/get_studentinclass.php")
    Call<List<Students>> getStudentinClass_admin(
            @Field("idClass") String idClass
    );
    @FormUrlEncoded
    @POST("class/delete_studentinclass.php")
    Call<Student_Teacher_Class_Point> delete_student_class(
            @Field("idClass") String idClass,
            @Field("idStu") String idStu
    );
    @FormUrlEncoded
    @POST("class/addteacher_class.php")
    Call<Student_Teacher_Class_Point> connective_teacher_class(
            @Field("idClass") String idClass,
            @Field("codeTeacher") String codeTeacher
    );
    @FormUrlEncoded
    @POST("class/get_teacherinclass.php")
    Call<List<Teacher>> getTeacherinClass_admin(
            @Field("idClass") String idClass
    );
    @FormUrlEncoded
    @POST("class/delete_teacherinclass.php")
    Call<Student_Teacher_Class_Point> delete_teacher_class(
            @Field("idClass") String idClass,
            @Field("codeTeacher") String codeTeacher
    );
    @FormUrlEncoded
    @POST("class/studentwaitinclass.php")
    Call<List<Students>> getStuwait_inClass(
            @Field("idClass") String idClass

    );
    @FormUrlEncoded
    @POST("class/addstuwait_class.php")
    Call<Student_Teacher_Class_Point> addStuwait_inClass(
            @Field("idClass") String idClass,
            @Field("idStudent") String idStudent
    );
    @FormUrlEncoded
    @POST("class/getAllrequirement.php")
    Call<List<Requirement>> getallRequirement_class_admin(
            @Field("keyword") String keyword
    );
    @FormUrlEncoded
    @POST("class/addrequiment_class.php")
    Call<Requirement> addRequirement_class_admin(
            @Field("idClass") String idClass,
            @Field("idReq") String idReq

    );
    @FormUrlEncoded
    @POST("class/getrequirementinclass.php")
    Call<List<Requirement>> getRequimentinClass_admin(
            @Field("idClass") String idClass
    );
    @FormUrlEncoded
    @POST("class/delete_reqinclass.php")
    Call<Requirement> deleteRequimentinClass_admin(
            @Field("idClass") String idClass,
            @Field("idReq") String idReq
    );
    @FormUrlEncoded
    @POST("class/adstu_req_point.php")
    Call<Student_Teacher_Class_Point> addStu_Req_Point(
            @Field("idClass") String idClass,
            @Field("idReq") String idReq,
            @Field("idStudent") String idStudent
    );
    @FormUrlEncoded
    @POST("class/getpointstu_class.php")
    Call<List<Points>> getPointStu_inClass_admin(
            @Field("idClass") String idClass,
            @Field("idStu") String idStu
    );
    @FormUrlEncoded
    @POST("class/updatepoint_student.php")
    Call<Points> updatePointStu_inClass_admin(
            @Field("idClass") String idClass,
            @Field("idStu") String idStu,
            @Field("idReq") String idReq,
            @Field("point") String point
    );
    //Admin<->Class
    //..........
    //student

    @FormUrlEncoded
    @POST("student/show_student.php")
    Call<List<Students>> getDetailStudent(
            @Field("emailStudent") String emailStudent
    );
    @FormUrlEncoded
    @POST("admin/update_inf_student_admin.php")
    Call<Students> updateDetailStudent(
            @Field("emailUser") String emailUser,
            @Field("codeStudent") String codeStudent,
            @Field("nameStudent") String nameStudent,
            @Field("birthdayStudent") String birthdayStudent,
            @Field("major") String major,
            @Field("phoneUser") String phoneUser
    );
    @FormUrlEncoded
    @POST("student/getallpoint_student.php")
    Call<List<Points>> getallPointStudent(
            @Field("idStu") String idStu
    );
    @FormUrlEncoded
    @POST("student/get_joinedClass.php")
    Call<List<Classs>> getJoinedClasses(
            @Field("idStudent") String idStudent);
    @FormUrlEncoded
    @POST("student/get_course_detail.php")
    Call<List<Classs>> getCourseDetail(
            @Field("id") String id);
    @GET("student/get_courseIT.php")
    Call<List<Classs>> getCourseIT_student();
    @GET("student/get_courseLanguage.php")
    Call<List<Classs>> getCourseLanguage_student();
    @GET("student/get_courseDesign.php")
    Call<List<Classs>> getCourseDesign_student();
    @FormUrlEncoded
    @POST("student/check_registered_course.php")
    Call<Classs> checkRegisteredCourse(
            @Field("idStu") String idStu,
            @Field("idClass") String idClass);
    //
    //teacher
    @FormUrlEncoded
    @POST("teacher/getclass_teached.php")
    Call<List<Classs>> getClass_teached_teacher(
            @Field("codeTeacher") String codeTeacher
    );
    @FormUrlEncoded
    @POST("teacher/getteacher_email.php")
    Call<List<Teacher>> getTeacher_email_teacher(
            @Field("emailteacher") String emailteacher
    );
    //
    //points
    @FormUrlEncoded
    @POST("points/get_Point_Student.php")
    Call<List<List<Points>>> getKN_LVPointStudent(
            @Field("idStu") String idStu
    );

    //get All idStu
    @GET("points/get_idstu.php")
    Call<List<Students>> getAllIdStu();

    //update All Student Point
    @FormUrlEncoded
    @POST("points/update_totalpoint.php")
    Call<Students> updateAllStuPoint(
            @Field("idStu") String idStu,
            @Field("point") String point
    );

    //company
    @FormUrlEncoded
    @POST("company/getCompany.php")
    Call<List<Companys>> getCompany(
            @Field("emailCompany") String emailCompany
    );
    @FormUrlEncoded
    @POST("company/update_inforCom.php")
    Call<Companys> UpdateCompanyinfor(
            @Field("idCompany") String idCompany,
            @Field("nameCompany") String nameCompany,
            @Field("addressCompany") String addressCompany
    );
    ///search major
    @FormUrlEncoded
    @POST("company/search_groupMajor.php")
    Call<List<MajorItem>> searchGMajor_Com(
            @Field("keyword") String keyword
    );
    ///search studen of major
    @FormUrlEncoded
    @POST("company/search_MajorStudent.php")
    Call<List<Students>> searchStudent_Of_Major(
            @Field("idgmajor") String idgmajor,
            @Field("keyword") String keyword

    );


}
