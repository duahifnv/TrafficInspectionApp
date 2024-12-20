package dstu.inspection.mapper;

import dstu.inspection.dto.ViolationDto;
import dstu.inspection.dto.inspect.LicenseDto;
import dstu.inspection.entity.License;
import dstu.inspection.entity.Violation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface LicenseMapper {
    @Mapping(target = "dateOfIssue", qualifiedByName = "strokeToDate")
    @Mapping(target = "dateOfExpiry", qualifiedByName = "strokeToDate")
    License dtoToModel(LicenseDto licenseDto);
    LicenseDto modelToDto(License license);
    @Named("strokeToDate")
    static Date strokeToDate(String strokeDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(strokeDate);
    }
    static String dateToStroke(Date date) {
        return date.toString();
    }
}
