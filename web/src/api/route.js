import { req } from './axiosFun';

/**
 *  景点信息
 **/
// 景点信息-获取景点列表
export const routeList = (params) => { return req("post",`/route/pageQuery/${params.page}/${params.limit}`, params) };
// 景点信息-景点添加
export const routeSave = (params) => { return req("post", "/route/save", params) };
// 景点信息-景点修改
export const routeUpdate = (params) => { return req("put", "/route/update", params) };
// 景点信息-删除景点
export const routeDelete = (params) => { return req("delete","/route/delete/"+params) };

// 查询所有景点信息
export const getRouteAll = () => { return req("get","/route/getRouteAll/") };
