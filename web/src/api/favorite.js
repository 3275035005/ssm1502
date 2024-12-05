import { req } from './axiosFun';

/**
 * 收藏信息
 **/
// 收藏信息-获取收藏列表
export const favoriteList = (params) => { return req("post",`/favorite/pageQuery/${params.page}/${params.limit}`, params) };
// 收藏信息-删除收藏
export const favoriteDelete = (params) => { return req("delete","/favorite/delete/"+params) };
