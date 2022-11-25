import { adminRoot } from './config';
import { UserRole } from '../utils/auth.roles';

const data = [
  {
    id: 'home',
    icon: 'iconsminds-home',
    label: 'menu.home',
    to: `${adminRoot}/home`,
  },
  {
    id: 'search',
    icon: 'iconsminds-magnifi-glass',
    label: 'menu.search',
    to: `${adminRoot}/search`,
    subs: [
      {
        icon: 'iconsminds-location-2',
        label: 'menu.area',
        to: `${adminRoot}/search/area`,
        // roles: [UserRole.Admin, UserRole.Editor],
      },
      {
        icon: 'iconsminds-letter-open',
        label: 'menu.name',
        to: `${adminRoot}/search/name`,
        // roles: [UserRole.Admin, UserRole.Editor],
      },
    ],
  },
  {
    id: 'favs',
    // icon: "iconsminds-reverbnation",
    icon: 'iconsminds-like',
    label: 'menu.favs',
    to: `${adminRoot}/favs`,
    subs: [
      {
        icon: 'iconsminds-location-2',
        label: 'menu.fArea',
        to: `${adminRoot}/favs/fArea`,
      },
      {
        icon: 'iconsminds-building',
        label: 'menu.fHouse',
        to: `${adminRoot}/favs/fHouse`,
      },
    ],
  },
  {
    id: 'support',
    icon: 'iconsminds-support',
    label: 'menu.support',
    to: `${adminRoot}/support`,
    subs: [
      {
        icon: 'simple-icon-user-following',
        label: 'menu.notice',
        to: `${adminRoot}/support/notice`,
      },
      {
        icon: 'simple-icon-user-following',
        label: 'menu.QnA',
        to: `${adminRoot}/support/QnA`,
      },
      {
        icon: 'simple-icon-user-following',
        label: 'menu.contact',
        to: `${adminRoot}/support/contact`,
      },
    ],
  },
];
export default data;
