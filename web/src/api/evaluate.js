import { req } from './axiosFun';

/**
 * 评价信息
 **/
// 评价信息-获取评价列表
export const evaluateList = (params) => { return req("post",`/evaluate/pageQuery/${params.page}/${params.limit}`, params) };
// 评价信息-删除评价
export const evaluateDelete = (params) => { return req("delete","/evaluate/delete/"+params) };
