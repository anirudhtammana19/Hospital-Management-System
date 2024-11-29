import { createSlice } from '@reduxjs/toolkit'
const user=createSlice(
    {name:"user",initialState:{token:"",userid:0,username:"",role:""},
    reducers:{
        login(state,action){
            const {token,userid,username,role}=action.payload
            state.token=token
            state.userid=userid
            state.username=username
            state.role=role
        },
        logout(state){
            state.token=""
            state.userid=0
            state.username=""
            state.role=""
        }
    }
})
export default user.reducer
export const {login,logout}=user.actions