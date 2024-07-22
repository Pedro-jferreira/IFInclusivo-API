package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.PK.ResolveuProblemaId;
import com.example.IfGoiano.IfCoders.model.ResolveuProblema;
import com.example.IfGoiano.IfCoders.repository.ResolveuProblemaRepository;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ResolveuProblemaService {
    @Autowired
    private ResolveuProblemaRepository resolveuProblemaRepository;

    public List<ResolveuProblema> findAll() {
        try {
            return resolveuProblemaRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all resolveuproblemas"+ e);
        }
    }

    public ResolveuProblema findById(ResolveuProblemaId id) {
        try {
            Optional<ResolveuProblema> resolveuProblema = resolveuProblemaRepository.findById(id);
            return resolveuProblema.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching resolveuProblema : " + e);
        }
    }

    @Transactional
    public ResolveuProblema save(ResolveuProblema resolveuProblema) {
        try {
            return resolveuProblemaRepository.save(resolveuProblema);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving the resolveuProblema"+ e);
        }
    }

    @Transactional
    public ResolveuProblema update(ResolveuProblemaId id, ResolveuProblema resolveuProblemaDetails) {
        try {
            ResolveuProblema resolveuProblema = findById(id);
            updateResolveuProblemaDetails(resolveuProblema,resolveuProblemaDetails);
            return resolveuProblemaRepository.save(resolveuProblema);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the resolveuProblema"+ e);
        }
    }

    @Transactional
    public void delete(ResolveuProblemaId id) {
        try {
            ResolveuProblema resolveuProblema = findById(id);
            resolveuProblemaRepository.delete(resolveuProblema);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the resolveuProblema"+ e);
        }
    }

    private void updateResolveuProblemaDetails(ResolveuProblema resolveuProblema, ResolveuProblema resolveuproblemaDetails) {
        resolveuProblema.setComentario(resolveuproblemaDetails.getComentario());
        resolveuProblema.setUsuario(resolveuproblemaDetails.getUsuario());
    }
}
