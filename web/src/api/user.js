import axios from 'axios';
import { req } from './axiosFun';

/**
 * 用户管理
 **/
// 用户管理-获取列表
export const userList = (params) => { return req("post", `/user/pageQuery/${params.page}/${params.limit}`, params) };
// 用户管理-保存（添加）
export const userSave = (params) => { return req("post", "/user/save", params) };
// 用户管理-保存（编辑）
export const userUpdate = (params) => { return req("put", "/user/update", params) };
// 用户管理-删除
export const userDelete = (params) => { return req("delete","/user/delete/"+params) };

export const getUserAll = () => { return req("get","/user/getUserAll") };
