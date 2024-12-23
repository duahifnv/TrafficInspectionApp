package dstu.inspection.mapper;

import dstu.inspection.dto.ViolationDto;
import dstu.inspection.dto.inspect.CertificateDto;
import dstu.inspection.entity.Certificate;
import dstu.inspection.entity.Violation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Mapper(componentModel = "spring")
public interface CertificateMapper {
    @Mapping(target = "dateOfRegistration", qualifiedByName = "strokeToDate")
    Certificate dtoToModel(CertificateDto certificateDto);
    @Mapping(target = "dateOfRegistration", qualifiedByName = "dateToStroke")
    CertificateDto modelToDto(Certificate certificate);
    @Named("strokeToDate")
    static Date strokeToDate(String strokeDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(strokeDate);
    }
    @Named("dateToStroke")
    static String dateToStroke(Date date) {
        return date.toString();
    }
}
