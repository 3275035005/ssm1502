import { req } from './axiosFun';

/**
 * 旅游攻略路线信息
 **/
// 旅游攻略路线信息-获取旅游攻略路线列表
export const strategyList = (params) => { return req("post",`/strategy/pageQuery/${params.page}/${params.limit}`, params) };
// 旅游攻略路线信息-旅游攻略路线添加
export const strategySave = (params) => { return req("post", "/strategy/save", params) };
// 旅游攻略路线信息-旅游攻略路线修改
export const strategyUpdate = (params) => { return req("put", "/strategy/update", params) };
// 旅游攻略路线信息-删除旅游攻略路线
export const strategyDelete = (params) => { return req("delete","/strategy/delete/"+params) };
