import { req } from './axiosFun';

/**
 * 留言信息
 **/
// 留言信息-获取留言列表
export const leaveList = (params) => { return req("post",`/leave/pageQuery/${params.page}/${params.limit}`, params) };
// 留言信息-留言回复
export const leaveUpdate = (params) => { return req("put", "/leave/update", params) };
// 留言信息-删除留言
export const leaveDelete = (params) => { return req("delete","/leave/delete/"+params) };
