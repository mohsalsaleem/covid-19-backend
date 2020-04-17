import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUserHealthStatus, defaultValue } from 'app/shared/model/user-health-status.model';

export const ACTION_TYPES = {
  FETCH_USERHEALTHSTATUS_LIST: 'userHealthStatus/FETCH_USERHEALTHSTATUS_LIST',
  FETCH_USERHEALTHSTATUS: 'userHealthStatus/FETCH_USERHEALTHSTATUS',
  CREATE_USERHEALTHSTATUS: 'userHealthStatus/CREATE_USERHEALTHSTATUS',
  UPDATE_USERHEALTHSTATUS: 'userHealthStatus/UPDATE_USERHEALTHSTATUS',
  DELETE_USERHEALTHSTATUS: 'userHealthStatus/DELETE_USERHEALTHSTATUS',
  RESET: 'userHealthStatus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserHealthStatus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type UserHealthStatusState = Readonly<typeof initialState>;

// Reducer

export default (state: UserHealthStatusState = initialState, action): UserHealthStatusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERHEALTHSTATUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERHEALTHSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERHEALTHSTATUS):
    case REQUEST(ACTION_TYPES.UPDATE_USERHEALTHSTATUS):
    case REQUEST(ACTION_TYPES.DELETE_USERHEALTHSTATUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERHEALTHSTATUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERHEALTHSTATUS):
    case FAILURE(ACTION_TYPES.CREATE_USERHEALTHSTATUS):
    case FAILURE(ACTION_TYPES.UPDATE_USERHEALTHSTATUS):
    case FAILURE(ACTION_TYPES.DELETE_USERHEALTHSTATUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERHEALTHSTATUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERHEALTHSTATUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERHEALTHSTATUS):
    case SUCCESS(ACTION_TYPES.UPDATE_USERHEALTHSTATUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERHEALTHSTATUS):
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

const apiUrl = 'api/user-health-statuses';

// Actions

export const getEntities: ICrudGetAllAction<IUserHealthStatus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERHEALTHSTATUS_LIST,
    payload: axios.get<IUserHealthStatus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IUserHealthStatus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERHEALTHSTATUS,
    payload: axios.get<IUserHealthStatus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUserHealthStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERHEALTHSTATUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserHealthStatus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERHEALTHSTATUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserHealthStatus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERHEALTHSTATUS,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
