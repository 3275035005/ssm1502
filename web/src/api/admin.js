import { req } from './axiosFun';

/**
 * 管理员信息
 **/
// 管理员信息-获取管理员列表
export const adminList = (params) => { return req("post",`/admin/pageQuery/${params.page}/${params.limit}`, params) };
// 管理员信息-管理员添加
export const adminSave = (params) => { return req("post", "/admin/save", params) };
// 管理员信息-管理员修改
export const adminUpdate = (params) => { return req("put", "/admin/update", params) };
// 管理员信息-删除管理员
export const adminDelete = (params) => { return req("delete","/admin/delete/"+params) };

// 查询详情信息
export const getInfo = (params) => { return req("get","/admin/getInfo/"+params) };
