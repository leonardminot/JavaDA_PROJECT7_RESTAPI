package com.nnk.springboot.controllers.restcontrollers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class CurveRestController {
    private final CurvePointService curvePointService;

    @Autowired
    public CurveRestController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @GetMapping("curves")
    public List<CurvePoint> getCurvePoints() {
        return curvePointService.getAll();
    }

    @GetMapping("curve/{id}")
    public CurvePoint getCurvePointById(@PathVariable("id")Integer id) {
        return curvePointService.getById(id);
    }

    @PostMapping("curve")
    public void addCurvePoint(@RequestBody @Valid CurvePoint curvePoint) {
        curvePointService.add(curvePoint);
    }

    @PutMapping("curve")
    public void updateCurvePoint(@RequestBody @Valid CurvePoint curvePoint) {
        curvePointService.update(curvePoint, curvePoint.getCurveId());
    }

    @DeleteMapping("curve/{id}")
    public void deleteCurvePoint(@PathVariable("id") Integer id) {
        curvePointService.delete(id);
    }
}
