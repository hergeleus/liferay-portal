/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.expando.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.impl.ExpandoColumnImpl;
import com.liferay.portlet.expando.model.impl.ExpandoColumnModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the expando column service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoColumnPersistence
 * @see ExpandoColumnUtil
 * @generated
 */
public class ExpandoColumnPersistenceImpl extends BasePersistenceImpl<ExpandoColumn>
	implements ExpandoColumnPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExpandoColumnUtil} to access the expando column persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExpandoColumnImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TABLEID = new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED,
			ExpandoColumnImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTableId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID =
		new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED,
			ExpandoColumnImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTableId", new String[] { Long.class.getName() },
			ExpandoColumnModelImpl.TABLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TABLEID = new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTableId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_T_N = new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED,
			ExpandoColumnImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByT_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_N = new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED,
			ExpandoColumnImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByT_N",
			new String[] { Long.class.getName(), String.class.getName() },
			ExpandoColumnModelImpl.TABLEID_COLUMN_BITMASK |
			ExpandoColumnModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_N = new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED,
			ExpandoColumnImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED,
			ExpandoColumnImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the expando column in the entity cache if it is enabled.
	 *
	 * @param expandoColumn the expando column
	 */
	public void cacheResult(ExpandoColumn expandoColumn) {
		EntityCacheUtil.putResult(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnImpl.class, expandoColumn.getPrimaryKey(),
			expandoColumn);

		expandoColumn.resetOriginalValues();
	}

	/**
	 * Caches the expando columns in the entity cache if it is enabled.
	 *
	 * @param expandoColumns the expando columns
	 */
	public void cacheResult(List<ExpandoColumn> expandoColumns) {
		for (ExpandoColumn expandoColumn : expandoColumns) {
			if (EntityCacheUtil.getResult(
						ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
						ExpandoColumnImpl.class, expandoColumn.getPrimaryKey()) == null) {
				cacheResult(expandoColumn);
			}
			else {
				expandoColumn.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all expando columns.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExpandoColumnImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExpandoColumnImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the expando column.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExpandoColumn expandoColumn) {
		EntityCacheUtil.removeResult(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnImpl.class, expandoColumn.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ExpandoColumn> expandoColumns) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExpandoColumn expandoColumn : expandoColumns) {
			EntityCacheUtil.removeResult(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
				ExpandoColumnImpl.class, expandoColumn.getPrimaryKey());
		}
	}

	/**
	 * Creates a new expando column with the primary key. Does not add the expando column to the database.
	 *
	 * @param columnId the primary key for the new expando column
	 * @return the new expando column
	 */
	public ExpandoColumn create(long columnId) {
		ExpandoColumn expandoColumn = new ExpandoColumnImpl();

		expandoColumn.setNew(true);
		expandoColumn.setPrimaryKey(columnId);

		return expandoColumn;
	}

	/**
	 * Removes the expando column with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param columnId the primary key of the expando column
	 * @return the expando column that was removed
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn remove(long columnId)
		throws NoSuchColumnException, SystemException {
		return remove(Long.valueOf(columnId));
	}

	/**
	 * Removes the expando column with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the expando column
	 * @return the expando column that was removed
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoColumn remove(Serializable primaryKey)
		throws NoSuchColumnException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ExpandoColumn expandoColumn = (ExpandoColumn)session.get(ExpandoColumnImpl.class,
					primaryKey);

			if (expandoColumn == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchColumnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(expandoColumn);
		}
		catch (NoSuchColumnException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ExpandoColumn removeImpl(ExpandoColumn expandoColumn)
		throws SystemException {
		expandoColumn = toUnwrappedModel(expandoColumn);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, expandoColumn);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(expandoColumn);

		return expandoColumn;
	}

	@Override
	public ExpandoColumn updateImpl(
		com.liferay.portlet.expando.model.ExpandoColumn expandoColumn,
		boolean merge) throws SystemException {
		expandoColumn = toUnwrappedModel(expandoColumn);

		boolean isNew = expandoColumn.isNew();

		ExpandoColumnModelImpl expandoColumnModelImpl = (ExpandoColumnModelImpl)expandoColumn;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, expandoColumn, merge);

			expandoColumn.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ExpandoColumnModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((expandoColumnModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(expandoColumnModelImpl.getOriginalTableId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TABLEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID,
					args);

				args = new Object[] {
						Long.valueOf(expandoColumnModelImpl.getTableId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TABLEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID,
					args);
			}

			if ((expandoColumnModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(expandoColumnModelImpl.getOriginalTableId()),
						
						expandoColumnModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_N,
					args);

				args = new Object[] {
						Long.valueOf(expandoColumnModelImpl.getTableId()),
						
						expandoColumnModelImpl.getName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_N,
					args);
			}
		}

		EntityCacheUtil.putResult(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
			ExpandoColumnImpl.class, expandoColumn.getPrimaryKey(),
			expandoColumn);

		return expandoColumn;
	}

	protected ExpandoColumn toUnwrappedModel(ExpandoColumn expandoColumn) {
		if (expandoColumn instanceof ExpandoColumnImpl) {
			return expandoColumn;
		}

		ExpandoColumnImpl expandoColumnImpl = new ExpandoColumnImpl();

		expandoColumnImpl.setNew(expandoColumn.isNew());
		expandoColumnImpl.setPrimaryKey(expandoColumn.getPrimaryKey());

		expandoColumnImpl.setColumnId(expandoColumn.getColumnId());
		expandoColumnImpl.setCompanyId(expandoColumn.getCompanyId());
		expandoColumnImpl.setTableId(expandoColumn.getTableId());
		expandoColumnImpl.setName(expandoColumn.getName());
		expandoColumnImpl.setType(expandoColumn.getType());
		expandoColumnImpl.setDefaultData(expandoColumn.getDefaultData());
		expandoColumnImpl.setTypeSettings(expandoColumn.getTypeSettings());

		return expandoColumnImpl;
	}

	/**
	 * Returns the expando column with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the expando column
	 * @return the expando column
	 * @throws com.liferay.portal.NoSuchModelException if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoColumn findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the expando column with the primary key or throws a {@link com.liferay.portlet.expando.NoSuchColumnException} if it could not be found.
	 *
	 * @param columnId the primary key of the expando column
	 * @return the expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn findByPrimaryKey(long columnId)
		throws NoSuchColumnException, SystemException {
		ExpandoColumn expandoColumn = fetchByPrimaryKey(columnId);

		if (expandoColumn == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + columnId);
			}

			throw new NoSuchColumnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				columnId);
		}

		return expandoColumn;
	}

	/**
	 * Returns the expando column with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the expando column
	 * @return the expando column, or <code>null</code> if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExpandoColumn fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the expando column with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param columnId the primary key of the expando column
	 * @return the expando column, or <code>null</code> if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn fetchByPrimaryKey(long columnId)
		throws SystemException {
		ExpandoColumn expandoColumn = (ExpandoColumn)EntityCacheUtil.getResult(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
				ExpandoColumnImpl.class, columnId);

		if (expandoColumn == _nullExpandoColumn) {
			return null;
		}

		if (expandoColumn == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				expandoColumn = (ExpandoColumn)session.get(ExpandoColumnImpl.class,
						Long.valueOf(columnId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (expandoColumn != null) {
					cacheResult(expandoColumn);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ExpandoColumnModelImpl.ENTITY_CACHE_ENABLED,
						ExpandoColumnImpl.class, columnId, _nullExpandoColumn);
				}

				closeSession(session);
			}
		}

		return expandoColumn;
	}

	/**
	 * Returns all the expando columns where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @return the matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByTableId(long tableId)
		throws SystemException {
		return findByTableId(tableId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando columns where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @return the range of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByTableId(long tableId, int start, int end)
		throws SystemException {
		return findByTableId(tableId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando columns where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByTableId(long tableId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TABLEID;
			finderArgs = new Object[] { tableId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TABLEID;
			finderArgs = new Object[] { tableId, start, end, orderByComparator };
		}

		List<ExpandoColumn> list = (List<ExpandoColumn>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_EXPANDOCOLUMN_WHERE);

			query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				list = (List<ExpandoColumn>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first expando column in the ordered set where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a matching expando column could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn findByTableId_First(long tableId,
		OrderByComparator orderByComparator)
		throws NoSuchColumnException, SystemException {
		List<ExpandoColumn> list = findByTableId(tableId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("tableId=");
			msg.append(tableId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchColumnException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last expando column in the ordered set where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a matching expando column could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn findByTableId_Last(long tableId,
		OrderByComparator orderByComparator)
		throws NoSuchColumnException, SystemException {
		int count = countByTableId(tableId);

		List<ExpandoColumn> list = findByTableId(tableId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("tableId=");
			msg.append(tableId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchColumnException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the expando columns before and after the current expando column in the ordered set where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param columnId the primary key of the current expando column
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn[] findByTableId_PrevAndNext(long columnId,
		long tableId, OrderByComparator orderByComparator)
		throws NoSuchColumnException, SystemException {
		ExpandoColumn expandoColumn = findByPrimaryKey(columnId);

		Session session = null;

		try {
			session = openSession();

			ExpandoColumn[] array = new ExpandoColumnImpl[3];

			array[0] = getByTableId_PrevAndNext(session, expandoColumn,
					tableId, orderByComparator, true);

			array[1] = expandoColumn;

			array[2] = getByTableId_PrevAndNext(session, expandoColumn,
					tableId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExpandoColumn getByTableId_PrevAndNext(Session session,
		ExpandoColumn expandoColumn, long tableId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXPANDOCOLUMN_WHERE);

		query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(tableId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(expandoColumn);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExpandoColumn> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the expando columns that the user has permission to view where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @return the matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByTableId(long tableId)
		throws SystemException {
		return filterFindByTableId(tableId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando columns that the user has permission to view where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @return the range of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByTableId(long tableId, int start,
		int end) throws SystemException {
		return filterFindByTableId(tableId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando columns that the user has permissions to view where tableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByTableId(long tableId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByTableId(tableId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ExpandoColumnModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ExpandoColumn.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ExpandoColumnImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ExpandoColumnImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(tableId);

			return (List<ExpandoColumn>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the expando columns before and after the current expando column in the ordered set of expando columns that the user has permission to view where tableId = &#63;.
	 *
	 * @param columnId the primary key of the current expando column
	 * @param tableId the table ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn[] filterFindByTableId_PrevAndNext(long columnId,
		long tableId, OrderByComparator orderByComparator)
		throws NoSuchColumnException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByTableId_PrevAndNext(columnId, tableId,
				orderByComparator);
		}

		ExpandoColumn expandoColumn = findByPrimaryKey(columnId);

		Session session = null;

		try {
			session = openSession();

			ExpandoColumn[] array = new ExpandoColumnImpl[3];

			array[0] = filterGetByTableId_PrevAndNext(session, expandoColumn,
					tableId, orderByComparator, true);

			array[1] = expandoColumn;

			array[2] = filterGetByTableId_PrevAndNext(session, expandoColumn,
					tableId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExpandoColumn filterGetByTableId_PrevAndNext(Session session,
		ExpandoColumn expandoColumn, long tableId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ExpandoColumnModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ExpandoColumn.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ExpandoColumnImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ExpandoColumnImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(tableId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(expandoColumn);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExpandoColumn> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the expando columns where tableId = &#63; and name = &#63;.
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @return the matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByT_N(long tableId, String name)
		throws SystemException {
		return findByT_N(tableId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the expando columns where tableId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @return the range of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByT_N(long tableId, String name, int start,
		int end) throws SystemException {
		return findByT_N(tableId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando columns where tableId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByT_N(long tableId, String name, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_N;
			finderArgs = new Object[] { tableId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_N;
			finderArgs = new Object[] {
					tableId, name,
					
					start, end, orderByComparator
				};
		}

		List<ExpandoColumn> list = (List<ExpandoColumn>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_EXPANDOCOLUMN_WHERE);

			query.append(_FINDER_COLUMN_T_N_TABLEID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_T_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_T_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_T_N_NAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<ExpandoColumn>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first expando column in the ordered set where tableId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a matching expando column could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn findByT_N_First(long tableId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchColumnException, SystemException {
		List<ExpandoColumn> list = findByT_N(tableId, name, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("tableId=");
			msg.append(tableId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchColumnException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last expando column in the ordered set where tableId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a matching expando column could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn findByT_N_Last(long tableId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchColumnException, SystemException {
		int count = countByT_N(tableId, name);

		List<ExpandoColumn> list = findByT_N(tableId, name, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("tableId=");
			msg.append(tableId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchColumnException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the expando columns before and after the current expando column in the ordered set where tableId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param columnId the primary key of the current expando column
	 * @param tableId the table ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn[] findByT_N_PrevAndNext(long columnId, long tableId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchColumnException, SystemException {
		ExpandoColumn expandoColumn = findByPrimaryKey(columnId);

		Session session = null;

		try {
			session = openSession();

			ExpandoColumn[] array = new ExpandoColumnImpl[3];

			array[0] = getByT_N_PrevAndNext(session, expandoColumn, tableId,
					name, orderByComparator, true);

			array[1] = expandoColumn;

			array[2] = getByT_N_PrevAndNext(session, expandoColumn, tableId,
					name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExpandoColumn getByT_N_PrevAndNext(Session session,
		ExpandoColumn expandoColumn, long tableId, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXPANDOCOLUMN_WHERE);

		query.append(_FINDER_COLUMN_T_N_TABLEID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_T_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_T_N_NAME_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(tableId);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(expandoColumn);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExpandoColumn> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the expando columns where tableId = &#63; and name = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param names the names
	 * @return the matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByT_N(long tableId, String[] names)
		throws SystemException {
		return findByT_N(tableId, names, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the expando columns where tableId = &#63; and name = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param names the names
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @return the range of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByT_N(long tableId, String[] names,
		int start, int end) throws SystemException {
		return findByT_N(tableId, names, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando columns where tableId = &#63; and name = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param names the names
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findByT_N(long tableId, String[] names,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_N;
			finderArgs = new Object[] { tableId, StringUtil.merge(names) };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_N;
			finderArgs = new Object[] {
					tableId, StringUtil.merge(names),
					
					start, end, orderByComparator
				};
		}

		List<ExpandoColumn> list = (List<ExpandoColumn>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_EXPANDOCOLUMN_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_T_N_TABLEID_5);

			conjunctionable = true;

			if ((names == null) || (names.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < names.length; i++) {
					String name = names[i];

					if (name == null) {
						query.append(_FINDER_COLUMN_T_N_NAME_4);
					}
					else {
						if (name.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_T_N_NAME_6);
						}
						else {
							query.append(_FINDER_COLUMN_T_N_NAME_5);
						}
					}

					if ((i + 1) < names.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				if (names != null) {
					qPos.add(names);
				}

				list = (List<ExpandoColumn>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns all the expando columns that the user has permission to view where tableId = &#63; and name = &#63;.
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @return the matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByT_N(long tableId, String name)
		throws SystemException {
		return filterFindByT_N(tableId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando columns that the user has permission to view where tableId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @return the range of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByT_N(long tableId, String name,
		int start, int end) throws SystemException {
		return filterFindByT_N(tableId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando columns that the user has permissions to view where tableId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByT_N(long tableId, String name,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_N(tableId, name, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_N_TABLEID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_T_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_T_N_NAME_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ExpandoColumnModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ExpandoColumn.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ExpandoColumnImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ExpandoColumnImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(tableId);

			if (name != null) {
				qPos.add(name);
			}

			return (List<ExpandoColumn>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the expando columns before and after the current expando column in the ordered set of expando columns that the user has permission to view where tableId = &#63; and name = &#63;.
	 *
	 * @param columnId the primary key of the current expando column
	 * @param tableId the table ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next expando column
	 * @throws com.liferay.portlet.expando.NoSuchColumnException if a expando column with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExpandoColumn[] filterFindByT_N_PrevAndNext(long columnId,
		long tableId, String name, OrderByComparator orderByComparator)
		throws NoSuchColumnException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_N_PrevAndNext(columnId, tableId, name,
				orderByComparator);
		}

		ExpandoColumn expandoColumn = findByPrimaryKey(columnId);

		Session session = null;

		try {
			session = openSession();

			ExpandoColumn[] array = new ExpandoColumnImpl[3];

			array[0] = filterGetByT_N_PrevAndNext(session, expandoColumn,
					tableId, name, orderByComparator, true);

			array[1] = expandoColumn;

			array[2] = filterGetByT_N_PrevAndNext(session, expandoColumn,
					tableId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExpandoColumn filterGetByT_N_PrevAndNext(Session session,
		ExpandoColumn expandoColumn, long tableId, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_N_TABLEID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_T_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_T_N_NAME_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ExpandoColumnModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ExpandoColumn.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ExpandoColumnImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ExpandoColumnImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(tableId);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(expandoColumn);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExpandoColumn> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the expando columns that the user has permission to view where tableId = &#63; and name = any &#63;.
	 *
	 * @param tableId the table ID
	 * @param names the names
	 * @return the matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByT_N(long tableId, String[] names)
		throws SystemException {
		return filterFindByT_N(tableId, names, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando columns that the user has permission to view where tableId = &#63; and name = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param names the names
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @return the range of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByT_N(long tableId, String[] names,
		int start, int end) throws SystemException {
		return filterFindByT_N(tableId, names, start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando columns that the user has permission to view where tableId = &#63; and name = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tableId the table ID
	 * @param names the names
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> filterFindByT_N(long tableId, String[] names,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_N(tableId, names, start, end, orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_T_N_TABLEID_5);

		conjunctionable = true;

		if ((names == null) || (names.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < names.length; i++) {
				String name = names[i];

				if (name == null) {
					query.append(_FINDER_COLUMN_T_N_NAME_4);
				}
				else {
					if (name.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_T_N_NAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_T_N_NAME_5);
					}
				}

				if ((i + 1) < names.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ExpandoColumnModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ExpandoColumn.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ExpandoColumnImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ExpandoColumnImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(tableId);

			if (names != null) {
				qPos.add(names);
			}

			return (List<ExpandoColumn>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the expando columns.
	 *
	 * @return the expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the expando columns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @return the range of expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the expando columns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExpandoColumn> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ExpandoColumn> list = (List<ExpandoColumn>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXPANDOCOLUMN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXPANDOCOLUMN.concat(ExpandoColumnModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ExpandoColumn>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ExpandoColumn>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the expando columns where tableId = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTableId(long tableId) throws SystemException {
		for (ExpandoColumn expandoColumn : findByTableId(tableId)) {
			remove(expandoColumn);
		}
	}

	/**
	 * Removes all the expando columns where tableId = &#63; and name = &#63; from the database.
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByT_N(long tableId, String name)
		throws SystemException {
		for (ExpandoColumn expandoColumn : findByT_N(tableId, name)) {
			remove(expandoColumn);
		}
	}

	/**
	 * Removes all the expando columns from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ExpandoColumn expandoColumn : findAll()) {
			remove(expandoColumn);
		}
	}

	/**
	 * Returns the number of expando columns where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @return the number of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTableId(long tableId) throws SystemException {
		Object[] finderArgs = new Object[] { tableId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TABLEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EXPANDOCOLUMN_WHERE);

			query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TABLEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of expando columns that the user has permission to view where tableId = &#63;.
	 *
	 * @param tableId the table ID
	 * @return the number of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByTableId(long tableId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByTableId(tableId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_EXPANDOCOLUMN_WHERE);

		query.append(_FINDER_COLUMN_TABLEID_TABLEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ExpandoColumn.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(tableId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of expando columns where tableId = &#63; and name = &#63;.
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @return the number of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_N(long tableId, String name) throws SystemException {
		Object[] finderArgs = new Object[] { tableId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_T_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EXPANDOCOLUMN_WHERE);

			query.append(_FINDER_COLUMN_T_N_TABLEID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_T_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_T_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_T_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				if (name != null) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_N, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of expando columns where tableId = &#63; and name = any &#63;.
	 *
	 * @param tableId the table ID
	 * @param names the names
	 * @return the number of matching expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_N(long tableId, String[] names)
		throws SystemException {
		Object[] finderArgs = new Object[] { tableId, StringUtil.merge(names) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_T_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_EXPANDOCOLUMN_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_T_N_TABLEID_5);

			conjunctionable = true;

			if ((names == null) || (names.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < names.length; i++) {
					String name = names[i];

					if (name == null) {
						query.append(_FINDER_COLUMN_T_N_NAME_4);
					}
					else {
						if (name.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_T_N_NAME_6);
						}
						else {
							query.append(_FINDER_COLUMN_T_N_NAME_5);
						}
					}

					if ((i + 1) < names.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tableId);

				if (names != null) {
					qPos.add(names);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_N, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of expando columns that the user has permission to view where tableId = &#63; and name = &#63;.
	 *
	 * @param tableId the table ID
	 * @param name the name
	 * @return the number of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByT_N(long tableId, String name)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByT_N(tableId, name);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_EXPANDOCOLUMN_WHERE);

		query.append(_FINDER_COLUMN_T_N_TABLEID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_T_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_T_N_NAME_2);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ExpandoColumn.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(tableId);

			if (name != null) {
				qPos.add(name);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of expando columns that the user has permission to view where tableId = &#63; and name = any &#63;.
	 *
	 * @param tableId the table ID
	 * @param names the names
	 * @return the number of matching expando columns that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByT_N(long tableId, String[] names)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByT_N(tableId, names);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_EXPANDOCOLUMN_WHERE);

		boolean conjunctionable = false;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_T_N_TABLEID_5);

		conjunctionable = true;

		if ((names == null) || (names.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < names.length; i++) {
				String name = names[i];

				if (name == null) {
					query.append(_FINDER_COLUMN_T_N_NAME_4);
				}
				else {
					if (name.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_T_N_NAME_6);
					}
					else {
						query.append(_FINDER_COLUMN_T_N_NAME_5);
					}
				}

				if ((i + 1) < names.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ExpandoColumn.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(tableId);

			if (names != null) {
				qPos.add(names);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of expando columns.
	 *
	 * @return the number of expando columns
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EXPANDOCOLUMN);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the expando column persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.expando.model.ExpandoColumn")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ExpandoColumn>> listenersList = new ArrayList<ModelListener<ExpandoColumn>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ExpandoColumn>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ExpandoColumnImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = ExpandoColumnPersistence.class)
	protected ExpandoColumnPersistence expandoColumnPersistence;
	@BeanReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;
	@BeanReference(type = ExpandoTablePersistence.class)
	protected ExpandoTablePersistence expandoTablePersistence;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_EXPANDOCOLUMN = "SELECT expandoColumn FROM ExpandoColumn expandoColumn";
	private static final String _SQL_SELECT_EXPANDOCOLUMN_WHERE = "SELECT expandoColumn FROM ExpandoColumn expandoColumn WHERE ";
	private static final String _SQL_COUNT_EXPANDOCOLUMN = "SELECT COUNT(expandoColumn) FROM ExpandoColumn expandoColumn";
	private static final String _SQL_COUNT_EXPANDOCOLUMN_WHERE = "SELECT COUNT(expandoColumn) FROM ExpandoColumn expandoColumn WHERE ";
	private static final String _FINDER_COLUMN_TABLEID_TABLEID_2 = "expandoColumn.tableId = ?";
	private static final String _FINDER_COLUMN_T_N_TABLEID_2 = "expandoColumn.tableId = ? AND ";
	private static final String _FINDER_COLUMN_T_N_TABLEID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_T_N_TABLEID_2) + ")";
	private static final String _FINDER_COLUMN_T_N_NAME_1 = "expandoColumn.name IS NULL";
	private static final String _FINDER_COLUMN_T_N_NAME_2 = "expandoColumn.name = ?";
	private static final String _FINDER_COLUMN_T_N_NAME_3 = "(expandoColumn.name IS NULL OR expandoColumn.name = ?)";
	private static final String _FINDER_COLUMN_T_N_NAME_4 = "(" +
		_removeConjunction(_FINDER_COLUMN_T_N_NAME_1) + ")";
	private static final String _FINDER_COLUMN_T_N_NAME_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_T_N_NAME_2) + ")";
	private static final String _FINDER_COLUMN_T_N_NAME_6 = "(" +
		_removeConjunction(_FINDER_COLUMN_T_N_NAME_3) + ")";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "expandoColumn.columnId";
	private static final String _FILTER_SQL_SELECT_EXPANDOCOLUMN_WHERE = "SELECT DISTINCT {expandoColumn.*} FROM ExpandoColumn expandoColumn WHERE ";
	private static final String _FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {ExpandoColumn.*} FROM (SELECT DISTINCT expandoColumn.columnId FROM ExpandoColumn expandoColumn WHERE ";
	private static final String _FILTER_SQL_SELECT_EXPANDOCOLUMN_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN ExpandoColumn ON TEMP_TABLE.columnId = ExpandoColumn.columnId";
	private static final String _FILTER_SQL_COUNT_EXPANDOCOLUMN_WHERE = "SELECT COUNT(DISTINCT expandoColumn.columnId) AS COUNT_VALUE FROM ExpandoColumn expandoColumn WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "expandoColumn";
	private static final String _FILTER_ENTITY_TABLE = "ExpandoColumn";
	private static final String _ORDER_BY_ENTITY_ALIAS = "expandoColumn.";
	private static final String _ORDER_BY_ENTITY_TABLE = "ExpandoColumn.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExpandoColumn exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ExpandoColumn exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(ExpandoColumnPersistenceImpl.class);
	private static ExpandoColumn _nullExpandoColumn = new ExpandoColumnImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ExpandoColumn> toCacheModel() {
				return _nullExpandoColumnCacheModel;
			}
		};

	private static CacheModel<ExpandoColumn> _nullExpandoColumnCacheModel = new CacheModel<ExpandoColumn>() {
			public ExpandoColumn toEntityModel() {
				return _nullExpandoColumn;
			}
		};
}