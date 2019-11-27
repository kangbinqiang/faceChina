/**
 * @author Jun Luo
 * @date 30/10/2019 17:08
 */
package com.facechina.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}