package com.conygre.spring.boot.services;

import com.conygre.spring.boot.entities.CompactDisc;
import com.conygre.spring.boot.repos.CompactDiscRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompactDiscServiceImplTest {

    @Mock
    private CompactDiscRepository dao;

    @InjectMocks
    private CompactDiscServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCatalog() {
        List<CompactDisc> mockCatalog = new ArrayList<>();
        when(dao.findAll()).thenReturn(mockCatalog);

        Iterable<CompactDisc> result = service.getCatalog();

        assertNotNull(result);
        assertEquals(mockCatalog, result);
        verify(dao, times(1)).findAll();
    }

    @Test
    void testGetCompactDiscById_Found() {
        CompactDisc mockDisc = new CompactDisc();
        when(dao.findById(1)).thenReturn(Optional.of(mockDisc));

        CompactDisc result = service.getCompactDiscById(1);

        assertNotNull(result);
        assertEquals(mockDisc, result);
        verify(dao, times(1)).findById(1);
    }

    @Test
    void testGetCompactDiscById_NotFound() {
        when(dao.findById(1)).thenReturn(Optional.empty());

        CompactDisc result = service.getCompactDiscById(1);

        assertNull(result);
        verify(dao, times(1)).findById(1);
    }

    @Test
    void testAddNewCompactDisc() {
        CompactDisc mockDisc = new CompactDisc();
        when(dao.save(mockDisc)).thenReturn(mockDisc);

        CompactDisc result = service.addNewCompactDisc(mockDisc);

        assertNotNull(result);
        assertEquals(mockDisc, result);
        verify(dao, times(1)).save(mockDisc);
    }

    @Test
    void testUpdateCompactDisc() {
        CompactDisc mockDisc = new CompactDisc();
        when(dao.save(mockDisc)).thenReturn(mockDisc);

        CompactDisc result = service.updateCompactDisc(mockDisc);

        assertNotNull(result);
        assertEquals(mockDisc, result);
        verify(dao, times(1)).save(mockDisc);
    }

    @Test
    void testDeleteCompactDiscById() {
        CompactDisc mockDisc = new CompactDisc();
        when(dao.findById(1)).thenReturn(Optional.of(mockDisc));
        doNothing().when(dao).delete(mockDisc);

        service.deleteCompactDisc(1);

        verify(dao, times(1)).findById(1);
        verify(dao, times(1)).delete(mockDisc);
    }

    @Test
    void testDeleteCompactDiscByObject() {
        CompactDisc mockDisc = new CompactDisc();
        doNothing().when(dao).delete(mockDisc);

        service.deleteCompactDisc(mockDisc);

        verify(dao, times(1)).delete(mockDisc);
    }

    @Test
    void testGetCatalog_Empty() {
        when(dao.findAll()).thenReturn(new ArrayList<>());

        Iterable<CompactDisc> result = service.getCatalog();

        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
        verify(dao, times(1)).findAll();
    }
}