package com.yjs.cloud.learning.learn.biz.category.mapper;

import com.yjs.cloud.learning.learn.biz.category.entity.Category;
import com.yjs.cloud.learning.learn.biz.category.bean.CategoryAdminListRequest;
import com.yjs.cloud.learning.learn.biz.category.bean.CategoryListRequest;
import com.yjs.cloud.learning.learn.biz.category.bean.CategoryResponse;
import com.yjs.cloud.learning.learn.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品类目 Mapper 接口
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
public interface CategoryMapper extends IBaseMapper<Category> {

    /**
     * 获取子类目
     * @param categoryListRequest 参数
     * @return 子类目列表
     */
    @Select({
        "<script>" +
        "   select " +
        "       distinct c.id, c.name, c.sort_order, c.level, c.is_show, c.is_show_index, " +
        "       c.image, c.create_time, c.update_time, direct_father_category_id pid " +
        "   from t_category c " +
        "       join t_category_relation cc on c.id = cc.child_category_id " +
        "   where cc.father_category_id = #{params.id} and c.is_show = 1 " +
        "   <if test='params.fetchAll == null or params.fetchAll == false'>and cc.is_sub = 1 </if>" +
        "   <if test='params.isShowIndex != null'>and c.is_show_index = #{params.isShowIndex } </if>" +
        "   order by c.sort_order desc, c.create_time " +
        "</script>"
    })
    List<CategoryResponse> selectList(@Param("params") CategoryListRequest categoryListRequest);

    /**
     * 获取类目列表
     * @param categoryAdminListRequest 请求参数
     * @return 类目列表
     */
    @Select({
        "<script>" +
        "   select " +
        "       distinct c.id, c.name, c.sort_order, c.level, c.is_show, c.is_show_index, " +
        "       c.image, c.create_time, c.update_time, direct_father_category_id pid " +
        "   from t_category c " +
        "   join t_category_relation cc on c.id = cc.child_category_id " +
        "   where " +
        "       cc.father_category_id = #{params.id} or cc.child_category_id = #{params.id} " +
        "       <if test='params.fetchAll == null or params.fetchAll == false'>" +
        "       and cc.is_sub = 1 " +
        "       </if>" +
        "</script>"
    })
    List<CategoryResponse> selectAdminList(@Param("params") CategoryAdminListRequest categoryAdminListRequest);

}
