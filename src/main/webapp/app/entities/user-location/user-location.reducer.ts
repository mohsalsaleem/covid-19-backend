import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUserLocation, defaultValue } from 'app/shared/model/user-location.model';

export const ACTION_TYPES = {
  FETCH_USERLOCATION_LIST: 'userLocation/FETCH_USERLOCATION_LIST',
  FETCH_USERLOCATION: 'userLocation/FETCH_USERLOCATION',
  CREATE_USERLOCATION: 'userLocation/CREATE_USERLOCATION',
  UPDATE_USERLOCATION: 'userLocation/UPDATE_USERLOCATION',
  DELETE_USERLOCATION: 'userLocation/DELETE_USERLOCATION',
  RESET: 'userLocation/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserLocation>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type UserLocationState = Readonly<typeof initialState>;

// Reducer

export default (state: UserLocationState = initialState, action): UserLocationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERLOCATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERLOCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERLOCATION):
    case REQUEST(ACTION_TYPES.UPDATE_USERLOCATION):
    case REQUEST(ACTION_TYPES.DELETE_USERLOCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERLOCATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERLOCATION):
    case FAILURE(ACTION_TYPES.CREATE_USERLOCATION):
    case FAILURE(ACTION_TYPES.UPDATE_USERLOCATION):
    case FAILURE(ACTION_TYPES.DELETE_USERLOCATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERLOCATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERLOCATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERLOCATION):
    case SUCCESS(ACTION_TYPES.UPDATE_USERLOCATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERLOCATION):
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

const apiUrl = 'api/user-locations';

// Actions

export const getEntities: ICrudGetAllAction<IUserLocation> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERLOCATION_LIST,
    payload: axios.get<IUserLocation>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IUserLocation> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERLOCATION,
    payload: axios.get<IUserLocation>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUserLocation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERLOCATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserLocation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERLOCATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserLocation> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERLOCATION,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
