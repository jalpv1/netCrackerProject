package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.RecoverCode;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.CodeRecoverQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeRowMapper implements RowMapper<RecoverCode> {
	@Override
	public RecoverCode mapRow(ResultSet resultSet, int i) throws SQLException {
		RecoverCode recoverCode = new RecoverCode();
		recoverCode.setEmail(resultSet.getString(CodeRecoverQuery.EMAIL));
		recoverCode.setCode(resultSet.getString(CodeRecoverQuery.CODE));
		recoverCode.setDate(resultSet.getDate(CodeRecoverQuery.GENERATE_DATE));
		return recoverCode;
	}
}
