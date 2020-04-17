import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUserTravelHistory, defaultValue } from 'app/shared/model/user-travel-history.model';

export const ACTION_TYPES = {
  FETCH_USERTRAVELHISTORY_LIST: 'userTravelHistory/FETCH_USERTRAVELHISTORY_LIST',
  FETCH_USERTRAVELHISTORY: 'userTravelHistory/FETCH_USERTRAVELHISTORY',
  CREATE_USERTRAVELHISTORY: 'userTravelHistory/CREATE_USERTRAVELHISTORY',
  UPDATE_USERTRAVELHISTORY: 'userTravelHistory/UPDATE_USERTRAVELHISTORY',
  DELETE_USERTRAVELHISTORY: 'userTravelHistory/DELETE_USERTRAVELHISTORY',
  RESET: 'userTravelHistory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserTravelHistory>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type UserTravelHistoryState = Readonly<typeof initialState>;

// Reducer

export default (state: UserTravelHistoryState = initialState, action): UserTravelHistoryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERTRAVELHISTORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERTRAVELHISTORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERTRAVELHISTORY):
    case REQUEST(ACTION_TYPES.UPDATE_USERTRAVELHISTORY):
    case REQUEST(ACTION_TYPES.DELETE_USERTRAVELHISTORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERTRAVELHISTORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERTRAVELHISTORY):
    case FAILURE(ACTION_TYPES.CREATE_USERTRAVELHISTORY):
    case FAILURE(ACTION_TYPES.UPDATE_USERTRAVELHISTORY):
    case FAILURE(ACTION_TYPES.DELETE_USERTRAVELHISTORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERTRAVELHISTORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERTRAVELHISTORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERTRAVELHISTORY):
    case SUCCESS(ACTION_TYPES.UPDATE_USERTRAVELHISTORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERTRAVELHISTORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/user-travel-histories';

// Actions

export const getEntities: ICrudGetAllAction<IUserTravelHistory> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERTRAVELHISTORY_LIST,
    payload: axios.get<IUserTravelHistory>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IUserTravelHistory> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERTRAVELHISTORY,
    payload: axios.get<IUserTravelHistory>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUserTravelHistory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERTRAVELHISTORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserTravelHistory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERTRAVELHISTORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserTravelHistory> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERTRAVELHISTORY,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
