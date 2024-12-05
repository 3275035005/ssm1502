import axios from 'axios';
import { loginreq, req } from './axiosFun';

// 登录接口
export const login = (params) => { return loginreq("post", "/login", params) };
// 获取用户菜单
export const menu = (params) => { return axios.get("/menu?&token=" + localStorage.getItem('logintoken')).then(res => res.data) };
// 退出接口
export const loginout = () => { return axios.delete("/login?&token=" + localStorage.getItem('getItem')).then(res => res.data) };
