import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './user-travel-history.reducer';
import { IUserTravelHistory } from 'app/shared/model/user-travel-history.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserTravelHistoryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserTravelHistoryUpdate = (props: IUserTravelHistoryUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { userTravelHistoryEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/user-travel-history' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateAndTimeOfTravel = convertDateTimeToServer(values.dateAndTimeOfTravel);

    if (errors.length === 0) {
      const entity = {
        ...userTravelHistoryEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="covid19App.userTravelHistory.home.createOrEditLabel">
            <Translate contentKey="covid19App.userTravelHistory.home.createOrEditLabel">Create or edit a UserTravelHistory</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : userTravelHistoryEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="user-travel-history-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="user-travel-history-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="userIdLabel" for="user-travel-history-userId">
                  <Translate contentKey="covid19App.userTravelHistory.userId">User Id</Translate>
                </Label>
                <AvField id="user-travel-history-userId" type="text" name="userId" />
              </AvGroup>
              <AvGroup>
                <Label id="locationNameLabel" for="user-travel-history-locationName">
                  <Translate contentKey="covid19App.userTravelHistory.locationName">Location Name</Translate>
                </Label>
                <AvField id="user-travel-history-locationName" type="text" name="locationName" />
              </AvGroup>
              <AvGroup>
                <Label id="latitudeLabel" for="user-travel-history-latitude">
                  <Translate contentKey="covid19App.userTravelHistory.latitude">Latitude</Translate>
                </Label>
                <AvField id="user-travel-history-latitude" type="string" className="form-control" name="latitude" />
              </AvGroup>
              <AvGroup>
                <Label id="longitudeLabel" for="user-travel-history-longitude">
                  <Translate contentKey="covid19App.userTravelHistory.longitude">Longitude</Translate>
                </Label>
                <AvField id="user-travel-history-longitude" type="string" className="form-control" name="longitude" />
              </AvGroup>
              <AvGroup>
                <Label id="dateAndTimeOfTravelLabel" for="user-travel-history-dateAndTimeOfTravel">
                  <Translate contentKey="covid19App.userTravelHistory.dateAndTimeOfTravel">Date And Time Of Travel</Translate>
                </Label>
                <AvInput
                  id="user-travel-history-dateAndTimeOfTravel"
                  type="datetime-local"
                  className="form-control"
                  name="dateAndTimeOfTravel"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.userTravelHistoryEntity.dateAndTimeOfTravel)}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/user-travel-history" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  userTravelHistoryEntity: storeState.userTravelHistory.entity,
  loading: storeState.userTravelHistory.loading,
  updating: storeState.userTravelHistory.updating,
  updateSuccess: storeState.userTravelHistory.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserTravelHistoryUpdate);
