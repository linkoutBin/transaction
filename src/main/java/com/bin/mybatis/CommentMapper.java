package com.bin.mybatis;

import com.bin.domain.*;
import org.apache.ibatis.annotations.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/24 5:49 PM
 */
public interface CommentMapper {

    @Select("select * from t_comment where id = #{cid}")
    Comment getById(@Param("cid") int id);
}
