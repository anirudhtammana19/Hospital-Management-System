import { configureStore } from '@reduxjs/toolkit'
import reducer from './UserSlice'
export const configstore=configureStore({reducer})