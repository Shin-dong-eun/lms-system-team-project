package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.constant.Gender;
import org.example.entity.Member;
import org.example.repository.MemberRepository;
import org.example.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {




    private final MemberService memberService;
    private final MemberRepository memberRepository;
    // 학생 메인 페이지
    @GetMapping("")
    public String stuMain(Principal principal, Model model) {
        // 학생 프로필에 이름 띄우기
        Member member = memberRepository.findByUserId(principal.getName());
        String name = (member != null) ? member.getUserName() : "Unknown";
        String savedProfile = (member != null) ? member.getImgSaved() : "Unknown";
        model.addAttribute("name", name);
        model.addAttribute("profileImg", savedProfile);
        System.out.println(savedProfile);

        return "/student/stud_main";
    }

    // 학생 성적 페이지
    @GetMapping("/grade")
    public String studentGrade() {
        return "/student/gradecheck";
    }

    // 학생 나의 강의현황
    @GetMapping("/lecture")
    public String studentLecture() {
        return "/student/mylecture";
    }

    // 학생 강의계획서 보기
    // 수정필요
    @GetMapping("/lectureplan")
    public String studentPlan() {
        return "/student/lecturePlan";
    }

    // 학생 마이페이지
    @GetMapping("/mypage")
    public String studentMyPage(Model model, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Member member = memberRepository.findByUserId(userDetails.getUsername());
        model.addAttribute("member", member);
        return "/student/stud_my_page";
    }

    @PostMapping("/mypage/modify")
    public String studentUpdate(Member member, Principal principal, @RequestPart MultipartFile file, Model model) throws Exception {
        Member memberT = memberService.memberView(principal.getName());
        memberT.setUserName(member.getUserName());
        memberT.setUserBirthday(member.getUserBirthday());
        memberT.setUserPhoneNum(member.getUserPhoneNum());
        memberT.setUserEmail(member.getUserEmail());
        memberT.setUserAddr(member.getUserAddr());

        //memberT.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.updateMember(memberT, file);

        model.addAttribute("message", "정보가 수정되었습니다.");
        model.addAttribute("SearchUrl", "/student");
        return "/student/message";
    }

    @GetMapping("/testattendance")
    public String test1() {
        return "/student/attendance";
    }

    @GetMapping("/test")
    public String test2() {
        return "/student/onlineclass";
    }

    /**
     * 학생 : 과제출제
     * @author 임휘재
     */
    @GetMapping("/assi")
    public String assiView(){
        return "/student/assiView";
    }

    @GetMapping("/scr")
    public String courseRegisteration() {
        return "/student/course_registeration";
    }


    /* 학생 나의 강의실 */
    @GetMapping("/mc")
    public String myClassroom() {
        return "/student/my_classroom";
    }
}
