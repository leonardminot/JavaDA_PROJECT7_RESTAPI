package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {
    private final CurvePointRepository curvePointRepository;
    private final DateProvider dateProvider;

    @Autowired
    public CurvePointService(CurvePointRepository curvePointRepository, DateProvider dateProvider) {
        this.curvePointRepository = curvePointRepository;
        this.dateProvider = dateProvider;
    }

    public CurvePoint add(CurvePoint curvePointToAdd) {
        return curvePointRepository.save(new CurvePoint(
                curvePointToAdd.getCurveId(),
                dateProvider.getNow(),
                curvePointToAdd.getTerm(),
                curvePointToAdd.getValue(),
                dateProvider.getNow()
        ));
    }

    public List<CurvePoint> getAll() {
        return curvePointRepository.findAll();
    }

    public CurvePoint getById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    public CurvePoint update(CurvePoint curvePoint, Integer id) {
        curvePointRepository.findById(id)
                .ifPresentOrElse(
                        existingCurvePoint -> {
                            existingCurvePoint.setCurveId(curvePoint.getCurveId());
                            existingCurvePoint.setValue(curvePoint.getValue());
                            existingCurvePoint.setTerm(curvePoint.getTerm());
                            curvePointRepository.save(existingCurvePoint);
                        },
                        () -> {
                            throw new RuntimeException("Curve point not found");
                        }
                );
        return curvePoint;
    }

    public void delete(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
