import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-travel-history.reducer';
import { IUserTravelHistory } from 'app/shared/model/user-travel-history.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserTravelHistoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserTravelHistoryDetail = (props: IUserTravelHistoryDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { userTravelHistoryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="covid19App.userTravelHistory.detail.title">UserTravelHistory</Translate> [
          <b>{userTravelHistoryEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="userId">
              <Translate contentKey="covid19App.userTravelHistory.userId">User Id</Translate>
            </span>
          </dt>
          <dd>{userTravelHistoryEntity.userId}</dd>
          <dt>
            <span id="locationName">
              <Translate contentKey="covid19App.userTravelHistory.locationName">Location Name</Translate>
            </span>
          </dt>
          <dd>{userTravelHistoryEntity.locationName}</dd>
          <dt>
            <span id="latitude">
              <Translate contentKey="covid19App.userTravelHistory.latitude">Latitude</Translate>
            </span>
          </dt>
          <dd>{userTravelHistoryEntity.latitude}</dd>
          <dt>
            <span id="longitude">
              <Translate contentKey="covid19App.userTravelHistory.longitude">Longitude</Translate>
            </span>
          </dt>
          <dd>{userTravelHistoryEntity.longitude}</dd>
          <dt>
            <span id="dateAndTimeOfTravel">
              <Translate contentKey="covid19App.userTravelHistory.dateAndTimeOfTravel">Date And Time Of Travel</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={userTravelHistoryEntity.dateAndTimeOfTravel} type="date" format={APP_DATE_FORMAT} />
          </dd>
        </dl>
        <Button tag={Link} to="/user-travel-history" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-travel-history/${userTravelHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ userTravelHistory }: IRootState) => ({
  userTravelHistoryEntity: userTravelHistory.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserTravelHistoryDetail);
