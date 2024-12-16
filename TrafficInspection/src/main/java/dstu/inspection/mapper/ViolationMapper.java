package dstu.inspection.mapper;

import dstu.inspection.dto.ViolationDto;
import dstu.inspection.entity.Violation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Mapper(componentModel = "spring")
public interface ViolationMapper {
    @Mapping(target = "dateOfViolation", qualifiedByName = "strokeToDate")
    @Mapping(target = "dateOfPayment", qualifiedByName = "strokeToDate")
    Violation dtoToModel(ViolationDto violationDto);
    ViolationDto modelToDto(Violation violation);
    @Named("strokeToDate")
    static Date strokeToDate(String strokeDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(strokeDate);
    }
    static String dateToStroke(Date date) {
        if (date == null) return null;
        return date.toString();
    }
}
