import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUserSymptom, defaultValue } from 'app/shared/model/user-symptom.model';

export const ACTION_TYPES = {
  FETCH_USERSYMPTOM_LIST: 'userSymptom/FETCH_USERSYMPTOM_LIST',
  FETCH_USERSYMPTOM: 'userSymptom/FETCH_USERSYMPTOM',
  CREATE_USERSYMPTOM: 'userSymptom/CREATE_USERSYMPTOM',
  UPDATE_USERSYMPTOM: 'userSymptom/UPDATE_USERSYMPTOM',
  DELETE_USERSYMPTOM: 'userSymptom/DELETE_USERSYMPTOM',
  RESET: 'userSymptom/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserSymptom>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type UserSymptomState = Readonly<typeof initialState>;

// Reducer

export default (state: UserSymptomState = initialState, action): UserSymptomState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERSYMPTOM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERSYMPTOM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERSYMPTOM):
    case REQUEST(ACTION_TYPES.UPDATE_USERSYMPTOM):
    case REQUEST(ACTION_TYPES.DELETE_USERSYMPTOM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERSYMPTOM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERSYMPTOM):
    case FAILURE(ACTION_TYPES.CREATE_USERSYMPTOM):
    case FAILURE(ACTION_TYPES.UPDATE_USERSYMPTOM):
    case FAILURE(ACTION_TYPES.DELETE_USERSYMPTOM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERSYMPTOM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERSYMPTOM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERSYMPTOM):
    case SUCCESS(ACTION_TYPES.UPDATE_USERSYMPTOM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERSYMPTOM):
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

const apiUrl = 'api/user-symptoms';

// Actions

export const getEntities: ICrudGetAllAction<IUserSymptom> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERSYMPTOM_LIST,
    payload: axios.get<IUserSymptom>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IUserSymptom> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERSYMPTOM,
    payload: axios.get<IUserSymptom>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUserSymptom> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERSYMPTOM,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserSymptom> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERSYMPTOM,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserSymptom> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERSYMPTOM,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
