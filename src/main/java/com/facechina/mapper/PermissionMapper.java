package test.IDao;

import test.domain.PermissionDO;
import test.domain.PermissionDOWithBLOBs;

public interface PermissionDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionDOWithBLOBs record);

    int insertSelective(PermissionDOWithBLOBs record);

    PermissionDOWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PermissionDOWithBLOBs record);

    int updateByPrimaryKey(PermissionDO record);
}