package com.fc.mini3server.service;

import com.fc.mini3server._core.handler.Message;
import com.fc.mini3server._core.handler.exception.Exception400;
import com.fc.mini3server.domain.*;
import com.fc.mini3server.dto.AdminRequestDTO;
import com.fc.mini3server.dto.ScheduleRequestDTO;
import com.fc.mini3server.dto.ScheduleResponseDTO;
import com.fc.mini3server.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Page<Schedule> findAnnualList(Pageable pageable) {
        return scheduleRepository.findByCategoryIsOrderById(CategoryEnum.ANNUAL, pageable);
    }

    public Page<Schedule> findDutyList(Pageable pageable) {
        return scheduleRepository.findByCategoryIsOrderById(CategoryEnum.DUTY, pageable);
    }

    @Transactional
    public void updateScheduleEvaluation(Long id, AdminRequestDTO.editEvaluationDTO requestDTO) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new Exception400(String.valueOf(id), Message.INVALID_ID_PARAMETER));

        schedule.updateEvaluation(requestDTO.getEvaluation());
    }

    public Page<ScheduleResponseDTO.ApprovedScheduleListDTO> getApprovedSchedule(Pageable pageable) {
        return scheduleRepository.findByEvaluation(EvaluationEnum.APPROVED, pageable)
                .map(ScheduleResponseDTO.ApprovedScheduleListDTO::of);
    }

    public Schedule createSchedule(ScheduleRequestDTO.createAnnualDTO createAnnualDTO, User user, Hospital hospital) {
        Schedule schedule = createAnnualDTO.toEntity(user, hospital);
        return scheduleRepository.save(schedule);
    }
}
