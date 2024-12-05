import { req } from './axiosFun';

/**
 * 公告信息
 **/
// 公告信息-获取公告列表
export const announcementList = (params) => { return req("post",`/announcement/pageQuery/${params.page}/${params.limit}`, params) };
// 公告信息-公告添加
export const announcementSave = (params) => { return req("post", "/announcement/save", params) };
// 公告信息-公告修改
export const announcementUpdate = (params) => { return req("put", "/announcement/update", params) };
// 公告信息-删除公告
export const announcementDelete = (params) => { return req("delete","/announcement/delete/"+params) };
