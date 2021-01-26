package com.springboot.dgumarket.service.report.ReportService;

import com.springboot.dgumarket.model.chat.ChatRoom;
import com.springboot.dgumarket.model.member.Member;
import com.springboot.dgumarket.model.product.Product;
import com.springboot.dgumarket.model.report.Report;
import com.springboot.dgumarket.model.report.ReportCategory;
import com.springboot.dgumarket.payload.request.report.ReportRequest;
import com.springboot.dgumarket.repository.chat.ChatRoomRepository;
import com.springboot.dgumarket.repository.member.MemberRepository;
import com.springboot.dgumarket.repository.product.ProductRepository;
import com.springboot.dgumarket.repository.report.ReportCategoryRepository;
import com.springboot.dgumarket.repository.report.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReportCategoryRepository reportCategoryRepository;

    @Autowired
    ReportRepository reportRepository;

    @Override
    public void postReport(int userId, ReportRequest reportRequest) {
        Member member = memberRepository.findById(userId);
        ReportCategory category = reportCategoryRepository.findById(reportRequest.getReport_category_id());

        Report report = Report.builder()
                .reporter(member)
                .reportEtcReason(reportRequest.getReport_etc_reason())
                .reportCategory(category).build();

        // 개별물건페이지에서 요청온 경우
        if (reportRequest.getReport_product_id().isPresent()){
            Product targetProduct = productRepository.getOne(reportRequest.getReport_product_id().get());
            report.setTargetUser(targetProduct.getMember());
            report.setReportProduct(targetProduct);
        }

        // 채팅방에서 요청온 경우
        if (reportRequest.getReport_room_id().isPresent()){
            ChatRoom chatRoom = chatRoomRepository.getOne(reportRequest.getReport_room_id().get());
            report.setTargetUser(chatRoom.getMemberOpponent(member));
            report.setChatRoom(chatRoom);
            report.setReportProduct(chatRoom.getProduct());
        }

        reportRepository.save(report); // 신고저장
    }
}