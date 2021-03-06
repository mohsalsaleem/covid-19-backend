import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './user-location.reducer';
import { IUserLocation } from 'app/shared/model/user-location.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserLocationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserLocationUpdate = (props: IUserLocationUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { userLocationEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/user-location' + props.location.search);
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
    if (errors.length === 0) {
      const entity = {
        ...userLocationEntity,
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
          <h2 id="covid19App.userLocation.home.createOrEditLabel">
            <Translate contentKey="covid19App.userLocation.home.createOrEditLabel">Create or edit a UserLocation</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : userLocationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="user-location-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="user-location-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="userIdLabel" for="user-location-userId">
                  <Translate contentKey="covid19App.userLocation.userId">User Id</Translate>
                </Label>
                <AvField id="user-location-userId" type="text" name="userId" />
              </AvGroup>
              <AvGroup>
                <Label id="latitudeLabel" for="user-location-latitude">
                  <Translate contentKey="covid19App.userLocation.latitude">Latitude</Translate>
                </Label>
                <AvField id="user-location-latitude" type="string" className="form-control" name="latitude" />
              </AvGroup>
              <AvGroup>
                <Label id="longitudeLabel" for="user-location-longitude">
                  <Translate contentKey="covid19App.userLocation.longitude">Longitude</Translate>
                </Label>
                <AvField id="user-location-longitude" type="string" className="form-control" name="longitude" />
              </AvGroup>
              <AvGroup>
                <Label id="locationNameLabel" for="user-location-locationName">
                  <Translate contentKey="covid19App.userLocation.locationName">Location Name</Translate>
                </Label>
                <AvField id="user-location-locationName" type="text" name="locationName" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/user-location" replace color="info">
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
  userLocationEntity: storeState.userLocation.entity,
  loading: storeState.userLocation.loading,
  updating: storeState.userLocation.updating,
  updateSuccess: storeState.userLocation.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserLocationUpdate);
