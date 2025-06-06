package com.conygre.spring.boot.rest;
import com.conygre.spring.boot.entities.CompactDisc;
import com.conygre.spring.boot.services.CompactDiscService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompactDiscControllerTest {

    @Mock
    private CompactDiscService service;

    @InjectMocks
    private CompactDiscController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Iterable<CompactDisc> mockCatalog = Collections.emptyList();
        when(service.getCatalog()).thenReturn(mockCatalog);

        Iterable<CompactDisc> result = controller.findAll();

        assertNotNull(result);
        assertEquals(mockCatalog, result);
        verify(service, times(1)).getCatalog();
    }

    @Test
    void testGetCdById_Found() {
        CompactDisc mockDisc = new CompactDisc();
        when(service.getCompactDiscById(1)).thenReturn(mockDisc);

        CompactDisc result = controller.getCdById(1);

        assertNotNull(result);
        assertEquals(mockDisc, result);
        verify(service, times(1)).getCompactDiscById(1);
    }

    @Test
    void testGetCdById_NotFound() {
        when(service.getCompactDiscById(1)).thenReturn(null);

        ResponseEntity<CompactDisc> result = controller.getByIdWith404(1);

        assertNotNull(result);
        assertEquals(404, result.getStatusCodeValue());
        verify(service, times(1)).getCompactDiscById(1);
    }
}