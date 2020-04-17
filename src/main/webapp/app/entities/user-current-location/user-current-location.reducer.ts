import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUserCurrentLocation, defaultValue } from 'app/shared/model/user-current-location.model';

export const ACTION_TYPES = {
  FETCH_USERCURRENTLOCATION_LIST: 'userCurrentLocation/FETCH_USERCURRENTLOCATION_LIST',
  FETCH_USERCURRENTLOCATION: 'userCurrentLocation/FETCH_USERCURRENTLOCATION',
  CREATE_USERCURRENTLOCATION: 'userCurrentLocation/CREATE_USERCURRENTLOCATION',
  UPDATE_USERCURRENTLOCATION: 'userCurrentLocation/UPDATE_USERCURRENTLOCATION',
  DELETE_USERCURRENTLOCATION: 'userCurrentLocation/DELETE_USERCURRENTLOCATION',
  RESET: 'userCurrentLocation/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserCurrentLocation>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type UserCurrentLocationState = Readonly<typeof initialState>;

// Reducer

export default (state: UserCurrentLocationState = initialState, action): UserCurrentLocationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERCURRENTLOCATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERCURRENTLOCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERCURRENTLOCATION):
    case REQUEST(ACTION_TYPES.UPDATE_USERCURRENTLOCATION):
    case REQUEST(ACTION_TYPES.DELETE_USERCURRENTLOCATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERCURRENTLOCATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERCURRENTLOCATION):
    case FAILURE(ACTION_TYPES.CREATE_USERCURRENTLOCATION):
    case FAILURE(ACTION_TYPES.UPDATE_USERCURRENTLOCATION):
    case FAILURE(ACTION_TYPES.DELETE_USERCURRENTLOCATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERCURRENTLOCATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERCURRENTLOCATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERCURRENTLOCATION):
    case SUCCESS(ACTION_TYPES.UPDATE_USERCURRENTLOCATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERCURRENTLOCATION):
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

const apiUrl = 'api/user-current-locations';

// Actions

export const getEntities: ICrudGetAllAction<IUserCurrentLocation> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERCURRENTLOCATION_LIST,
    payload: axios.get<IUserCurrentLocation>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IUserCurrentLocation> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERCURRENTLOCATION,
    payload: axios.get<IUserCurrentLocation>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUserCurrentLocation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERCURRENTLOCATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserCurrentLocation> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERCURRENTLOCATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserCurrentLocation> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERCURRENTLOCATION,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
