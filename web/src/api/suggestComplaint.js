import { req } from './axiosFun';

/**
 * 景点建议和投诉信息
 **/
// 景点建议和投诉信息-获取景点建议和投诉列表
export const suggestComplaintList = (params) => { return req("post",`/suggest-complaint/pageQuery/${params.page}/${params.limit}`, params) };

// 景点建议和投诉信息-删除景点建议和投诉
export const suggestComplaintDelete = (params) => { return req("delete","/suggest-complaint/delete/"+params) };
