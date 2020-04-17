import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import userCurrentLocation, {
  UserCurrentLocationState
} from 'app/entities/user-current-location/user-current-location.reducer';
// prettier-ignore
import userLocation, {
  UserLocationState
} from 'app/entities/user-location/user-location.reducer';
// prettier-ignore
import userHealthStatus, {
  UserHealthStatusState
} from 'app/entities/user-health-status/user-health-status.reducer';
// prettier-ignore
import userSymptom, {
  UserSymptomState
} from 'app/entities/user-symptom/user-symptom.reducer';
// prettier-ignore
import userTravelHistory, {
  UserTravelHistoryState
} from 'app/entities/user-travel-history/user-travel-history.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly userCurrentLocation: UserCurrentLocationState;
  readonly userLocation: UserLocationState;
  readonly userHealthStatus: UserHealthStatusState;
  readonly userSymptom: UserSymptomState;
  readonly userTravelHistory: UserTravelHistoryState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  userCurrentLocation,
  userLocation,
  userHealthStatus,
  userSymptom,
  userTravelHistory,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
