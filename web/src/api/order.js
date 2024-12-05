import { req } from './axiosFun';

/**
 * 订单信息
 **/
// 订单信息-获取订单列表
export const orderList = (params) => { return req("post",`/order/pageQuery/${params.page}/${params.limit}`, params) };
// 订单信息-处理订单
export const orderUpdate = (params) => { return req("put", "/order/update", params) };
// 订单信息-删除订单
export const orderDelete = (params) => { return req("delete","/order/delete/"+params) };
