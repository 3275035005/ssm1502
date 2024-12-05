import { req } from './axiosFun';

/**
 * 轮播图信息
 **/
// 轮播图信息-获取轮播图列表
export const bannerList = (params) => { return req("post",`/banner/pageQuery/${params.page}/${params.limit}`, params) };
// 轮播图信息-轮播图添加
export const bannerSave = (params) => { return req("post", "/banner/save", params) };
// 轮播图信息-轮播图修改
export const bannerUpdate = (params) => { return req("put", "/banner/update", params) };
// 轮播图信息-删除轮播图
export const bannerDelete = (params) => { return req("delete","/banner/delete/"+params) };
