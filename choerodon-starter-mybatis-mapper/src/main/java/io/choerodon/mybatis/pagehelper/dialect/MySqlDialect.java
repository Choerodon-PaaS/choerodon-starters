/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.choerodon.mybatis.pagehelper.dialect;

import io.choerodon.core.domain.PageInfo;
import org.apache.ibatis.cache.CacheKey;

/**
 * mysql分页方言类
 *
 * @author liuzh
 */
public class MySqlDialect extends AbstractHelperDialect {

    @Override
    public String getPageSql(String sql, PageInfo info, CacheKey pageKey) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        sqlBuilder.append(sql);
        if (info.getBegin() == 0) {
            sqlBuilder.append(" LIMIT ");
            sqlBuilder.append(info.getSize());
        } else {
            sqlBuilder.append(" LIMIT ");
            sqlBuilder.append(info.getBegin());
            sqlBuilder.append(",");
            sqlBuilder.append(info.getSize());
            pageKey.update(info.getBegin());
        }
        pageKey.update(info.getBegin());
        return sqlBuilder.toString();
    }
}
