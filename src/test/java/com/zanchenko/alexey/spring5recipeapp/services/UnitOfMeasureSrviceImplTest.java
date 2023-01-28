package com.zanchenko.alexey.spring5recipeapp.services;

import com.zanchenko.alexey.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.zanchenko.alexey.spring5recipeapp.converts.UnitOfMeasureToUnitOfMeasureCommand;
import com.zanchenko.alexey.spring5recipeapp.domain.UnitOfMeasure;
import com.zanchenko.alexey.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureSrviceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        service = new UnitOfMeasureSrviceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUoms() {
        // given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        // then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}